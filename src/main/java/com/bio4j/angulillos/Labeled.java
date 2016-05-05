package com.bio4j.angulillos;

interface HasLabel {

  default public String _label() {
    return getClass().getCanonicalName();
  }
}
