# clorum

Basic forum software written in Clojure.

Work-in-progress.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed, as well as MySQL, MariaDB, or equivalent.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

The database structure can be found in `clorum.sql`, which can be easily imported into a database.

## License

Copyright (c)2014 ipavl
