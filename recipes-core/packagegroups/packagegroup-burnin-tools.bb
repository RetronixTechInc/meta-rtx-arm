DESCRIPTION = "V3x/V4x specific packages"

LICENCE = "BSD-3-Clause & GPLv2+ & LGPLv2+"

inherit packagegroup

RDEPENDS_${PN} = " \
	cpuburn-arm \
	stressapptest \
"
