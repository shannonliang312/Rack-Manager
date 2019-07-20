SUMMARY = "GNU Project parser generator (yacc replacement)"
DESCRIPTION = "Bison is a general-purpose parser generator that converts an annotated context-free grammar into \
an LALR(1) or GLR parser for that grammar.  Bison is upward compatible with Yacc: all properly-written Yacc \
grammars ought to work with Bison with no change. Anyone familiar with Yacc should be able to use Bison with \
little trouble."
HOMEPAGE = "http://www.gnu.org/software/bison/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
SECTION = "devel"
DEPENDS = "bison-native flex-native"

SRC_URI = "${GNU_MIRROR}/bison/bison-${PV}.tar.xz \
           file://dont-depend-on-help2man.patch.patch \
           file://add-with-bisonlocaledir.patch \
"

# No point in hardcoding path to m4, just use PATH
EXTRA_OECONF += "M4=m4"

SRC_URI[md5sum] = "c9b552dee234b2f6b66e56b27e5234c9"
SRC_URI[sha256sum] = "039ee45b61d95e5003e7e8376f9080001b4066ff357bde271b7faace53b9d804"

inherit autotools gettext texinfo

# The automatic m4 path detection gets confused, so force the right value
acpaths = "-I ${S}/m4"

do_compile_prepend() {
	for i in mfcalc calc++ rpcalc; do mkdir -p ${B}/examples/$i; done
}

do_install_append_class-native() {
	create_wrapper ${D}/${bindir}/bison \
		BISON_PKGDATADIR=${STAGING_DATADIR_NATIVE}/bison
}
do_install_append_class-nativesdk() {
	create_wrapper ${D}/${bindir}/bison \
		BISON_PKGDATADIR=${datadir}/bison
}
BBCLASSEXTEND = "native nativesdk"
