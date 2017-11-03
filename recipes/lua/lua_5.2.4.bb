DESCRIPTION = "Lua is a powerful light-weight programming language designed \
for extending applications."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://doc/readme.html;beginline=363;endline=405;md5=92ced3317ff75cadaf4499b3bfeb6de4"
HOMEPAGE = "http://www.lua.org/"

PR = "r0"

DEPENDS = "readline"
SRC_URI = "http://www.lua.org/ftp/lua-${PV}.tar.gz \
           file://lua.pc \
"

SRC_URI[md5sum] = "913fdb32207046b273fdb17aad70be13"
SRC_URI[sha256sum] = "b9e2e4aad6789b3b63a056d442f7b39f0ecfca3ae0f1fc0ae4e9614401b69f4b"

inherit pkgconfig binconfig

UCLIBC_PATCHES += "file://uclibc-pthread.patch"
SRC_URI_append_libc-uclibc = "${UCLIBC_PATCHES}"

TARGET_CC_ARCH += " -fPIC ${LDFLAGS}"
EXTRA_OEMAKE = "'CC=${CC} -fPIC' 'MYCFLAGS=${CFLAGS} -DLUA_USE_LINUX -fPIC' MYLDFLAGS='${LDFLAGS}'"

do_configure_prepend() {
    sed -i -e s:/usr/local:${prefix}:g src/luaconf.h
}

do_compile () {
    oe_runmake linux
}

# Note that this recipe puts the lua include files into a "lua5.2" subdirectory,
# which resembles the way Ubuntu lua5.2-dev packages put them.  This makes it easier
# to cross develop between an Ubuntu host and a this target.
do_install () {
    oe_runmake \
        'INSTALL_TOP=${D}${prefix}' \
        'INSTALL_BIN=${D}${bindir}' \
        'INSTALL_INC=${D}${includedir}/lua5.2' \
        'INSTALL_MAN=${D}${mandir}/man1' \
        'INSTALL_SHARE=${D}${datadir}/lua' \
        'INSTALL_LIB=${D}${libdir}' \
        'INSTALL_CMOD=${D}${libdir}/lua/5.2' \
        install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua.pc ${D}${libdir}/pkgconfig/
    rmdir ${D}${datadir}/lua/5.2
    rmdir ${D}${datadir}/lua
}

BBCLASSEXTEND = "native"

