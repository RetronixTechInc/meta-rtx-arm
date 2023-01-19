FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
           file://fstab \
           "

do_install_append() {
	install -d ${D}/cse_data
	install -m 0644 ${WORKDIR}/fstab ${D}${sysconfdir}/fstab
}
