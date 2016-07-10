# clorum
[![Build Status](https://travis-ci.org/ipavl/clorum.svg?branch=master)](https://travis-ci.org/ipavl/clorum)
[![Dependency Status](https://www.versioneye.com/user/projects/547a9c29b22f9096bb0000e6//badge.svg?style=flat)](https://www.versioneye.com/user/projects/547a9c29b22f9096bb0000e6/)

Basic forum software written in Clojure.

Work-in-progress.

## Demo

A demo instance of Clorum is available on [Heroku](https://clorum.herokuapp.com/).

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed, as well as MySQL or PostgreSQL (see "Database Servers" below for details).

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

The database structure can be found in `clorum-{database-server}.sql`, which can be easily imported into a database.

## Database Servers

MySQL and PostgreSQL are currently supported out-of-the-box. PostgreSQL is the default and can be changed to MySQL by uncommenting the dependency in `project.clj` and setting `db` in `config.clj` to use the MySQL configuration found in that file instead of the PostgreSQL configuration.

## Documentation

Project documentation is generated with [Codox][] and can be found
[on GitHub Pages](https://ipavl.github.io/clorum/doc/), or generated locally by running:

    lein doc

[codox]: https://github.com/weavejester/codox

## License

Copyright © 2014-2016 ipavl

Distributed under the Eclipse Public License, the same as Clojure.
