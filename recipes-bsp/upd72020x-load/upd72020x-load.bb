DESCRIPTION = "renesas upd72020x firmware read/write tool"
LICENSE = "CLOSED"

SRC_URI = "file://upd72020x-load.c"

S = "${WORKDIR}"

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} upd72020x-load.c -o upd72020x-load
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 upd72020x-load ${D}${bindir}
}

