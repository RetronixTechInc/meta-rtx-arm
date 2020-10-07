SUMMARY = "A very basic Wayland image with a terminal"

IMAGE_FEATURES += "splash package-management hwcodecs"

LICENSE = "MIT"

inherit core-image distro_features_check

REQUIRED_DISTRO_FEATURES = "wayland"

CORE_IMAGE_BASE_INSTALL += "weston weston-init weston-examples gtk+3-demo clutter-1.0-examples"
CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

export IMAGE_NAME="${MACHINE}-${DATETIME}"
export IMAGE_LINK_NAME="${MACHINE}"

IMAGE_INSTALL += "	\
bash			\
net-tools		\
minicom			\
python			\
php			\
php-cli			\
perl			\
samba			\
ppp			\
openssl			\
openssh			\
apache2			\
appendcse		\
i2c-tools		\
util-linux		\
tzdata			\
alsa-utils		\
udev-extraconf		\
connman			\
initscript		\
imx-test		\
libgpiod		\
python-pygobject	\
gtk+3			\
python-pip		\
python3			\
python3-pip		\
gstreamer1.0-python	\
gobject-introspection	\
gstreamer1.0 		\
python3-pygobject	\
owfs			\
"
