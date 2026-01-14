DESCRIPTION = "V3x/V4x specific packages"

LICENSE = "BSD-3-Clause & GPLv2+ & LGPLv2+"

inherit packagegroup

RDEPENDS_${PN} = " \
	phytool \
	tcpdump \
	iputils \
	iw \
"
