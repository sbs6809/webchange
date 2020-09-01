(ns webchange.interpreter.events-register
  (:require
    [re-frame.core :as re-frame]))

(re-frame/reg-event-db
  ::register-animation
  (fn [db [_ name animation]]
    (let [scene-id (:current-scene db)]
      (assoc-in db [:scenes scene-id :animations name] animation))))

(re-frame/reg-event-db
  ::register-transition
  (fn [db [_ name component]]
    (let [scene-id (:current-scene db)]
      (assoc-in db [:transitions scene-id name] component))))
