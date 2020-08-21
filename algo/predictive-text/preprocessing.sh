#!/bin/sh

FILENAME="data.txt"
TARGET="data"

echo "Starting processing text"

ls $TARGET | xargs cat | egrep -v "<[a-zA-Z/]+>" | egrep -o "[a-zA-Z]+" | awk "BEGIN {ORS=\" \"} {print $1}" > "$TARGET/$FILENAME"

echo "Saving file to $TARGET/$FILENAME"