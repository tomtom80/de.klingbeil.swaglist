OUTPUT=$(helm ls --all $1)
if echo $OUTPUT | grep -q "$1"; then
    echo "true"
else
    echo "false"
fi