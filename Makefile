format:
	clojure -M:format

format-fix:
	clojure -M:format!

lint:
	clj-kondo --lint src

test:
	clojure -X:test

.PHONY: test
