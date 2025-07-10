DESCRIPTION = "Murata WiFi firmware files"
LICENSE = "CLOSED"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://brcm \
    file://murata_wifi \
    file://regulatory.db \
    file://regulatory.db.p7s \
"

# Ensure bash is included as a runtime dependency
RDEPENDS:${PN} += "bash"

do_install() {
    # Create necessary directories
    install -d ${D}${nonarch_base_libdir}/firmware/brcm
    install -d ${D}/usr/share/murata_wifi

    # Copy firmware files
    cp -r ${WORKDIR}/brcm/* ${D}${nonarch_base_libdir}/firmware/brcm/
    install -m 0644 ${WORKDIR}/regulatory.db ${D}${nonarch_base_libdir}/firmware/
    install -m 0644 ${WORKDIR}/regulatory.db.p7s ${D}${nonarch_base_libdir}/firmware/
    cp -r ${WORKDIR}/murata_wifi/* ${D}/usr/share/murata_wifi/

    # Set correct permissions
    chmod -R 755 ${D}${nonarch_base_libdir}/firmware/brcm
    chmod 644 ${D}${nonarch_base_libdir}/firmware/regulatory.db
    chmod 644 ${D}${nonarch_base_libdir}/firmware/regulatory.db.p7s
    chmod -R 755 ${D}/usr/share/murata_wifi

    # Ensure all scripts inside murata_wifi are executable
    find ${D}/usr/share/murata_wifi -type f -exec chmod 755 {} \;

    # Set ownership (not strictly necessary in Yocto but kept if your build expects it)
    chown -R root:root ${D}${nonarch_base_libdir}/firmware/brcm || true
    chown -R root:root ${D}/usr/share/murata_wifi || true
}

# Match installed paths correctly under usrmerge
FILES:${PN} += "${nonarch_base_libdir}/firmware/brcm \
                /usr/share/murata_wifi \
                ${nonarch_base_libdir}/firmware/regulatory.db \
                ${nonarch_base_libdir}/firmware/regulatory.db.p7s \
                "
