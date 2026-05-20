SUMMARY = "UPD720201 firmware"
LICENSE = "CLOSED"
PR = "r0"

SRC_URI = "file://K2026090-renesas_usb_fw.mem"

COMPATIBLE_MACHINE = "raptor|sparrowhawk"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}"

#Error message : ... doesn't have GNU_HASH (didn't pass LDFLAGS?) [ldflags]
TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/K2026090-renesas_usb_fw.mem ${D}${nonarch_base_libdir}/firmware/renesas_usb_fw.mem
}

#Error message : ... Files/directories were installed but not shipped in any package:
FILES:${PN} += "${nonarch_base_libdir}/firmware/renesas_usb_fw.mem"
