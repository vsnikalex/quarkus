package com.tsystems.lazy;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped  // => normal scope
class CoolService {
    String ping() {
        return "cool";
    }
}
