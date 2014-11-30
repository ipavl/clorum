# clorum
[![Build Status](https://travis-ci.org/ipavl/clorum.svg?branch=master)](https://travis-ci.org/ipavl/clorum)

Basic forum software written in Clojure.

Work-in-progress.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed, as well as MySQL, MariaDB, or equivalent.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

The database structure can be found in `clorum.sql`, which can be easily imported into a database.

## Documentation

Project documentation is generated with [Codox][] and can be found
[on GitHub Pages](https://ipavl.github.io/clorum/doc/), or generated locally by running:

    lein doc

[codox]: https://github.com/weavejester/codox

## License

Copyright (c)2014 ipavl
