//
//  LiveData.swift
//  ios-livedata
//
//  Created by Hans Dulimarta on 3/18/24.
//

import Foundation

typealias ObserverFunc<T> = (T?) -> Void

class LiveData<T> {
    private var _value: T?
    private var subscribers: Array<ObserverFunc<T>> = []

    init(_ value: T? = nil) {
        self._value = value
    }
    
    // Declare a property with setter and getter
    var value: T? {
        set(newValue) {
            self._value = newValue
            for fn in self.subscribers {
                // Notify each subscriber of the new value
                fn(newValue)
            }
        }
        get {
            return self._value
        }
    }
    
    func observe(_ obs: @escaping ObserverFunc<T>) {
        self.subscribers.append(obs)
        obs(self._value)
    }
}
