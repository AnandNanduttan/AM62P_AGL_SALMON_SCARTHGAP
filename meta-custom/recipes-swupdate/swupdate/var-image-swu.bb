DESCRIPTION = "SWUpdate image for AGL AM62PXX-EVM"
SECTION = ""

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Files to include in the .swu package
SRC_URI = " \
    file://sw-description \
    file://update.sh \
"

inherit swupdate

# Primary image to bundle
VAR_SWUPDATE_TARGET_IMAGE ??= "agl-ivi-demo-qt"
VAR_SWUPDATE_TARGET_IMAGE_FSTYPE = ".ext4.zst"
VAR_SWUPDATE_TARGET_IMAGE_FILE = "${VAR_SWUPDATE_TARGET_IMAGE}-${MACHINE}.rootfs${VAR_SWUPDATE_TARGET_IMAGE_FSTYPE}"

# Ensure image is built before .swu
IMAGE_DEPENDS = "${VAR_SWUPDATE_TARGET_IMAGE}"

# Files to include in .swu archive
SWUPDATE_IMAGES = "${VAR_SWUPDATE_TARGET_IMAGE_FILE}"

# Let BitBake know the expected image format
python() {
    d.setVarFlag("SWUPDATE_IMAGES_FSTYPES",
        d.getVar("VAR_SWUPDATE_TARGET_IMAGE"),
        d.getVar("VAR_SWUPDATE_TARGET_IMAGE_FSTYPE"))
}

# Ensure swupdate waits for rootfs to complete
do_compile[depends] += "${VAR_SWUPDATE_TARGET_IMAGE}:do_image_complete"

