SUMMARY = "The dnsproxy daemon is a proxy for DNS queries."

HOMEPAGE = "https://www.wolfermann.org/dnsproxy.html"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;md5=5e9fa35c795930c6ba06109c6c63622a"

DEPENDS = "libevent"

SRC_URI = "https://www.wolfermann.org/dnsproxy-${PV}.tar.gz "

SRC_URI[md5sum] = "19308fe5ec0f6a377de745d48b809bf1"
SRC_URI[sha256sum] = "59ceca8b8c81f105813d3fa65b803f1b00264b8f3ef03c0607a5f30145c0447a"

EXTRA_OEMAKE = "CC='${CC}' LD='${LD}' VPATH='../${PN}-${PV}' NROFF='/usr/bin/nroff'"


EXTRA_OECONF = " \
    --with-libevent=${STAGING_DIR_TARGET}/usr/ \
"

inherit autotools

do_configure_append () {
	cp ${WORKDIR}/dnsproxy-1.17/dnsproxy.1 ${WORKDIR}/build/dnsproxy.1
}

do_install() {
	install -d ${D}/${sbindir}
	install -m 555 ${WORKDIR}/build/dnsproxy ${D}/${sbindir}
	install -d ${D}/usr/share/man/cat1
	install -m 444 ${WORKDIR}/build/dnsproxy.cat1 ${D}/usr/share/man/cat1/dnsproxy.0
}
