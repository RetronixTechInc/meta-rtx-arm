# Copyright (C) 2013-2016 Freescale Semiconductor

DESCRIPTION = "U-Boot provided by Freescale with focus on  i.MX reference boards."
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"


SRCBRANCH = "RTX_2015.04"
SRC_URI = "git://github.com/RetronixTechInc/u-boot-rtx.git;protocol=https;branch=${SRCBRANCH} \
	"
SRCREV = "RTX_2015.04"
#SRCREV = "2bc265949514d5c574ae9694a8d71a6bc0aa1579"


S = "${WORKDIR}/git"

inherit fsl-u-boot-localversion

LOCALVERSION ?= "-${SRCBRANCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(mx6|mx6ul|mx7)"
