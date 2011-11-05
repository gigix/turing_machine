lein deps && \
lein test && \
lein uberjar && \
java -jar turing-machine-1.0.0-SNAPSHOT-standalone.jar ./machines/one_third.machine 10 && \
lein install