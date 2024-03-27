SUMMARY = "Linux driver for RTL8821AU chipsets"
LICENSE = "CLOSED"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit module

S = "${WORKDIR}/T2Uv3_Linux"

SRC_URI = "https://static.tp-link.com/2020/202012/20201225/Archer%20T2U_V3_201211_Linux.zip;downloadfilename=Archer_T2U_V3_201211_Linux.zip \
	file://0001-Makefile.patch \
	file://0002-ioctl-cfg80211.patch \
"
SRC_URI[md5sum] = "dc15ad08aacb6c9e7c7328d51a954a2c"
SRC_URI[sha256sum] = "0789fb40faaf2b4e9c56882ec48c2dca568b899be20cff4c1b0fbf0fe3fdbf53"

EXTRA_OEMAKE_append = " KSRC=${STAGING_KERNEL_DIR}"

do_install(){
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/realtek/rtl8821au
    install -m 755 ${S}/*.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/realtek/rtl8821au/
}	
