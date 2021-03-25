# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://chromium.desktop \
	"

do_install_append () {
	install -d ${D}/usr/share/applications/
	install -m 0644 ${WORKDIR}/chromium.desktop ${D}/usr/share/applications
}

