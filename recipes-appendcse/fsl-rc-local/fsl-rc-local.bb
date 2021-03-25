# look for files in the layer first
DESCRIPTION = "Extra files for fsl-rc-local"
LICENSE = "CLOSED"

SRC_URI += "file://rc.local	"

S = "${WORKDIR}"

do_install () {
	install -d ${D}/etc
	install -m 0755 ${S}/rc.local ${D}/etc/
}

