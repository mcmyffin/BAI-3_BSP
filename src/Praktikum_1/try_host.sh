#!/bin/zsh
# Testet die Erreichbarkeit des angegebenen Rechners
# Dimitri Meier 
# 13.04.2015


#-------------function-------------------
usage() {
echo "[-h|-s <sec>] <hostname>|<IP-Address>

   Try to ping every 10 secons (default without param) the
   <hostname> or <IP-Adress> and print the result:
         '<hostname>|<IP-Address> OK' if ping successfuly
        OR
         '<hostname>|<IP-Address> FAILED'

OPTIONS:
   -h: Display this help
   -s: <time in seconds>
"

}

do_ping() {

    while [ 0 ]
    do

        r=$(ping -w 1 -c 1 $host)
        RETVAL=$?
        if [ $RETVAL -eq 0 ]; then
            echo "$host OK"
        else
            echo "$host FAIL"
        fi
        sleep $interval
    done
}

#---------------main---------------------
if [ $# -eq 1 ]; then

    case $1 in
        "-h")
            usage
            exit 1
            ;;
        *)
            interval=10
            host=$1
            do_ping
            exit 1
    esac
elif [ $# -eq 3 ]; then
    case $2 in
        "")
            echo "<sec> ist empty"
            usage
            exit 1
            ;;
        *)
            interval=$2
            host=$3
            do_ping
            exit 1
    esac
else
    usage
    exit 1
fi

exit 0
#----------------------------------------