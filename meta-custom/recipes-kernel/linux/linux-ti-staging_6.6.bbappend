FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://am62p_devicetree.patch"

#PV = "6.1.83+gitAUTOINC+c1c2f1971f"
PR = "r1"

SRC_URI[md5sum] = "SKIP"
SRC_URI[sha256sum] = "SKIP"

