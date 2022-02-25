DESCRIPTION = "rtx demo image."
LICENSE = "MIT"

export IMAGE_NAME="${MACHINE}-${DATETIME}"
export IMAGE_LINK_NAME="${MACHINE}"

require recipes-core/images/core-image-base.bb

IMAGE_INSTALL += "	\
bash			\
net-tools		\
minicom			\
python			\
apache2			\
php			\
php-cli			\
perl			\
samba			\
ppp			\
openssl			\
openssh			\
i2c-tools		\
util-linux		\
tzdata			\
alsa-utils		\
udev-extraconf		\
imx-test		\
libgpiod		\
owfs			\
e2fsprogs		\
wvdial			\
appendcse		\
initscript		\
networkmanager		\
dhcp-server 		\
dhcp-client		\
init-ifupdown		\
rp-pppoe		\
ntpdate			\
miniupnpd		\
quagga			\
cronie			\
at			\
iptables		\
msmtp			\
squid			\
procps			\
iputils			\
iproute2		\
tcpdump			\
modemmanager		\
libmbim			\
sudo			\
ethtool			\
v4l-utils			\
yavta				\
imx-gst1.0-plugin-rtx		\
gstreamer1.0			\
gstreamer1.0-plugins-base	\
gstreamer1.0-plugins-good	\
gstreamer1.0-plugins-bad-rtx	\
gstreamer1.0-rtsp-server-rtx	\
gst-rtsp			\
fuse-exfat			\
exfat-utils			\
dnf			\
opencv				\
fetchmail			\
procmail			\
ffmpeg			\
srt			\
libsrtp			\
initled				\
"
