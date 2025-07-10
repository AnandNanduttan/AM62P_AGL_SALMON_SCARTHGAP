# Copyright (C) 2025
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "AGL Image with SWUpdate support for AM62Pxx-EVM. \
This image contains the AGL demo image and SWUpdate tooling for OTA update testing."

LICENSE = "MIT"

# Set the base image to your AGL image
require recipes-platform/images/agl-ivi-demo-qt.bb



# NOTE: This image is intended for development and testing of SWUpdate integration.
#       It is not hardened or optimized for production deployment.

# Add SWUpdate and supporting packages
CORE_IMAGE_EXTRA_INSTALL += " \
    swupdate \
    swupdate-www \
    kernel-image \
    kernel-devicetree \
"

# Don't generate UBI images (optional for AM62P which typically uses SD/eMMC)
IMAGE_FSTYPES:remove = "multiubi"

# Optional: remove other large formats to reduce image size
IMAGE_FSTYPES:remove += "wic wic.gz tar.gz"

# Optional: set image types explicitly if needed
IMAGE_FSTYPES += "ext4.xz"

# Optional: disable QBSP if not relevant
QBSP_IMAGE_CONTENT = ""

