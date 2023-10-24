if ! git rev-parse --is-inside-work-tree &> /dev/null; then
  echo "Not in a Git repository. Please navigate to a Git repository directory."
  exit 1
fi

git log --oneline -n 5 --format="%h"
