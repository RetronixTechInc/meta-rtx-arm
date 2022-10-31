SUMMARY = "Terminal-emulator State Machine"
DESCRIPTION = "Terminal-emulator State Machine"
HOMEPAGE = "https://www.freedesktop.org/wiki/Software/kmscon"
BUGTRACKER = "https://bugs.freedesktop.org/enter_bug.cgi?product=HarfBuzz"
SECTION = "libs"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=be41eca402c741d9a6384aea14c75ae3"

SRC_URI = "https://www.freedesktop.org/software/kmscon/releases/libtsm-3.tar.xz"

SRC_URI[md5sum] = "c1b297a69d11a72f207ec35ae5ce7d69"
SRC_URI[sha256sum] = "114115d84a2bc1802683871ea2d70a16ddeec8d2f8cde89ebd2046d775e6cf07"


inherit autotools pkgconfig
