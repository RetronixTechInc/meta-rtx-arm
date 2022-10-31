SUMMARY = "a fast FrameBuffer based TERMinal emulator for linux"

HOMEPAGE = "https://code.google.com/archive/p/fbterm/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README;md5=ecfd2f3949c214b2bd542b70e8bc0a0a"

SRC_URI = "file://lltd-${PV}.tar.gz"

SRC_URI[md5sum] = "77b2916c4578d88e64a5e13e29ea8c0c"
SRC_URI[sha256sum] = "af0de23f14257c4ef205458e81a98e0aec48945d3e9596f9cc6a6b1c36aa7c5f"

EXTRA_OEMAKE = " \
	CC='${CC}' \
	LD='${CC}' \
"

do_install () {
	install -d ${D}/usr/bin
	install -m 0755 ${WORKDIR}/lltd-1.0/lld2d ${D}/usr/bin/lld2d
	install -m 0755 ${WORKDIR}/lltd-1.0/lld2test ${D}/usr/bin/lld2test
}

do_package_qa[noexec] = "1"
