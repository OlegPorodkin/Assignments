let assignmentApi = Vue.resource('/assignments');

Vue.component('workSet-row', {
  props:['workSets'],
  template: '<div>' +
              '<div>{{ workSets }}</div>' +
            '</div>'
});

Vue.component('teacher-row', {
  props:['teacher'],
  template: '<div>' +
              '<div hidden>{{ teacher.id }}</div>' +
              '<div>{{ teacher.lastName }} {{ teacher.firstName }} {{ teacher.middleName }}</div>' +
              '<div>{{ teacher.contacts }}</div>' +
              '<div>{{ teacher.department }}</div>' +
              '<div>{{ teacher.info }}</div>' +
            '</div>'
});

Vue.component('assignment-row',{
  props:['assignment'],
  template: '<tr>' +
              '<td hidden>{{assignment.id}}</td>' +
              '<td>{{ assignment.title }}</td>' +
              '<td>' +
                '<teacher-row :teacher="assignment.teacher" :key="assignment.teacher.id"/>' +
              '</td>' +
              '<td><workSet-row :workSets="assignment.typeOfWorkSet"/></td>' +
              '<td>{{ assignment.task }}</td>' +
              '<td>{{ assignment.schedule }}</td>' +
              '<td>{{ assignment.resources }}</td>' +
            '</tr>'
});

Vue.component('assignments-table',{
  props:['assignments'],
  template: '<table border="1">'+
              '<tr>' +
                '<th hidden>id</th>' +
                '<th>Название предмета</th>' +
                '<th>Преподователь</th>' +
                '<th>Тип зачета</th>' +
                '<th>Задание</th>' +
                '<th>Расписание</th>' +
                '<th>Ресурсы</th>' +
              '</tr>' +
              '<assignment-row v-for="assignment in assignments" :assignment="assignment" :key="assignment.id" />'+
            '</table>',
});

let app = new Vue({
  el: '#app',
  template: '<assignments-table :assignments="assignments"/>',
  data: {
    assignments:[]
  },
  created: function () {
    assignmentApi.get().then(result =>{
      result
          .json()
          .then(data => data
              .forEach(assignment => this.assignments.push(assignment)))
    })
  }
});

