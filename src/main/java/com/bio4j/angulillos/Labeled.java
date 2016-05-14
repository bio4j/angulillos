package com.bio4j.angulillos;

interface HasLabel {

  default String _label() { return getClass().getCanonicalName(); }
}
