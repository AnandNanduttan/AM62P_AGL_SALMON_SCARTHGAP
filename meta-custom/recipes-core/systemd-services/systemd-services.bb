DESCRIPTION = "WiFi Systemd Services"
LICENSE = "CLOSED"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://startwlanap.service \
    file://startwlansta.service \
"

inherit systemd

S = "${WORKDIR}"

do_install() {
    install -d ${D}/etc/systemd/system/
    install -m 0644 ${WORKDIR}/startwlanap.service ${D}/etc/systemd/system/
    install -m 0644 ${WORKDIR}/startwlansta.service ${D}/etc/systemd/system/
}

SYSTEMD_SERVICE:${PN} = "startwlanap.service startwlansta.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

FILES:${PN} += "${systemd_system_unitdir}/*.service"
