
FILESEXTRAPATHS_prepend := "${THISDIR}/patches-openjdk-8:"
SRC_URI += "file://fix-makefile-version-check.patch \
	    file://fix-present-time-check.patch \
	"

CFLAGS_append = " -Wno-error=format-overflow -Wno-error=deprecated-declarations -Wno-error=return-type"

