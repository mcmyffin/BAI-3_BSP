#!/bin/zsh
# Hängt für alle Dateien im aktuellen Verzeichnis die Zeichenkette string an den aktuellen Dateinamen an (Umbenennung). 
# Dimitri Meier 
# 13.04.2015

# ------------------------------------------------------------
# This function shows the help text for this bash script
usage() {
PWD=`pwd`
echo "frename.sh <String>
  * Haengt im Pfad ($PWD/) allen *.txt Files den <String> an.

OPTIONS: 
   -h: Display this help
"
}

# This function rename all *.txt files
do_rename() {


	for file in $(ls | grep -a .txt)
	do
		echo $file
		echo "rename to $file$string"
		mv $file $file$string

	done    
}

# Read String that will be used to rename
get_string() {
    echo "Please enter your <String> which will be append to the *txt Files"
    read string
}
# ---------------------- main --------------------------------
# check parameters 
if [ $# -gt 1 ]; then
    usage
    exit 1
fi

case $1 in
    "-h")
        usage
        exit 0
        ;;
    "")
        get_string
        ;;
    *)
        string=$1
esac

do_rename

exit 0
# ---------------------- end ---------------------------------
