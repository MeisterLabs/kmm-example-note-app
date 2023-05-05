//
//  FlowPublisher.swift
//  iosApp
//
//  Created by Petra Cendelinova on 04.05.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import shared

public struct FlowPublisher<Output>: Publisher where Output: AnyObject {
    public typealias Flow = NativeFlow<Output>
    public typealias Failure = Never

    private let flow: Flow
    
    public init(flow: Flow) {
        self.flow = flow
    }

    public func receive<S: Subscriber>(subscriber: S) where S.Input == Output, S.Failure == Failure {
        let subscription = FlowSubscription(flow: flow, subscriber: subscriber)
        subscriber.receive(subscription: subscription)
    }
    
    final class FlowSubscription<S: Subscriber>: Subscription where S.Input == Output, S.Failure == Failure {
        private var subscriber: S?
        private var job: Kotlinx_coroutines_coreJob? = nil

        private let flow: Flow

        init(flow: Flow, subscriber: S) {
            self.flow = flow
            self.subscriber = subscriber
          
            job = flow.subscribe(
                onEach: { output in
                    if (output != nil) {
                        subscriber.receive(output!)
                    }
                },
                onComplete: { subscriber.receive(completion: .finished) },
                onThrow: { error in debugPrint(error) }
            )
        }
      
        func cancel() {
            subscriber = nil
            job?.cancel(cause: nil)
        }

        func request(_ demand: Subscribers.Demand) {}
    }
}

