(ns webchange.dashboard.students.subs
  (:require
    [re-frame.core :as re-frame]))

(re-frame/reg-sub
  ::class-students
  (fn [db [_ id]]
    (get-in db [:dashboard :students id])))

(re-frame/reg-sub
  ::current-student
  (fn [db]
    (get-in db [:dashboard :current-student])))