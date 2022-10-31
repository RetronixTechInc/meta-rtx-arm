SUMMARY = "KMS/DRM based System Console"
DESCRIPTION = "KMS/DRM based System Console"
HOMEPAGE = "https://www.freedesktop.org/wiki/Software/kmscon"
BUGTRACKER = "https://bugs.freedesktop.org/enter_bug.cgi?product=HarfBuzz"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=7b09c62eae0d1148fb579f810f85e897"

DEPENDS = "libxkbcommon libtsm udev libdrm"

SRC_URI = " \
	https://www.freedesktop.org/software/kmscon/releases/kmscon-8.tar.xz \
	file://0001_modify_single.patch \
	"

SRC_URI[md5sum] = "90d39c4ef53a11c53f27be4a7e9acee4"
SRC_URI[sha256sum] = "0ab01c1cdc4fbd692ce765e80478bc2d9663a7c55a5c75cc7ac421366ee6ae2b"

inherit autotools pkgconfig

