#!/bin/bash

read -p "Enter the file path: " filepath

keys=$(jq -r 'keys_unsorted | .[]' "$filepath")

echo "Keys found:"
for key in ${keys[@]}; do
    echo "*$key"
done

read -p "Enter the key you want to see: " userkey

if [[ "$keys" =~ "$userkey" ]]; then
    value=$(jq -r ".$userkey" "$filepath")
    echo "Values for '$userkey': $value"
else
    echo "No such key!"
    exit 1
fi
