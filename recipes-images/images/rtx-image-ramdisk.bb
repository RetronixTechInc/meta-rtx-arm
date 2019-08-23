DESCRIPTION = "Retronix console-only image with more full-featured Linux system \
functionality installed."

SUMMARY = "A small image just capable of allowing a device to boot."

# IMAGE_FEATURES += "splash ssh-server-openssh"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL_append += " \
wget \
curl \
bash \
e2fsprogs \
dosfstools \
rtxsetting \
unzip \
"

# IMAGE_ROOTFS_SIZE ?= "8192"
# IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"
