#CFLAGS = -static -O2 -Wall -Ih
#LDFLAGS = -static

TARGET=rtx_setting rtx_setenv

CFLAGS=-I$(WORKDIR)/h
#CFLAGS1=-D_REENTRANT  -I$(PKG_CONFIG_SYSROOT_DIR)/usr/include/libmad


all: $(TARGET)
rtx_setting: rtx_setting.o datalist.o crc32.o
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)

rtx_setenv: rtx_setenv.o datalist.o crc32.o
	$(CC) $(CFLAGS) -o $@ $^ $(LDFLAGS)

clean:
	rm -f $(OBJS) $(TARGET)




