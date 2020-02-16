package com.tsystems.lazy;

import javax.inject.Singleton;

@Singleton  // => pseudo-scope
class AmazingService {
    String ping() {
        return "amazing";
    }
}
