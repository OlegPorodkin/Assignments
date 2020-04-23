let assignmentApi = Vue.resource('/assignments');
let assignmentAddApi = Vue.resource('/assignments/add');
let teacherApi = Vue.resource('/teacher');

Vue.component('teacher-list', {
  props:['teacher'],
  template:
      '<div>' +
        '<select v-model="selectedTeacher">' +
          '<option disabled value="">Выберите одного из преподователей</option>' +
          '<option ' +
            // ':teacher="teacher" ' +
            ':value="teacher" ' +
            ':key="teacher.id">{{ teacher.fullName }}</option>' +
        '</select>' +
        '<div>{{ selectedTeacher }}</div>' +
      '</div>',
  data: function () {
    return {
      selectedTeacher:null,
  }},
});

Vue.component('workSet-checkbox', {
  props:['value', 'val'],
  template:
      '<div>' +
      '<label>' +
      '<input ' +
        'type="checkbox" ' +
        ':value="val" ' +
        'v-model="model">' +
      '<slot></slot>' +
      '</label>' +
      '</div>',
  computed:{
    model: {
      get() {
        return this.value;
      },
      set(val) {
        this.$emit('input', val);
      },
    },
  },
});

Vue.component('assignment-form', {
  props:['assignments'],
  data: function(){
    return{
      title:'',
      teacher:null,
      task:'',
      typeOfWorkSet:[],
      resources:'',
      schedule:'',
      typeOfWorkSetArr:[
        {value: 0, label: 'Контрольная работа'},
        {value: 1, label: 'Курсовая работа'},
        {value: 2, label: 'Курсовой проект'},
        {value: 3, label: 'Экзамен'},
        {value: 4, label: 'Не известно'},
      ],
      teachersArr:[],
    }
  },
  template: '<div>' +
              '<input type="text" placeholder="Название предмета" v-model="title"/><br/>' +
              '<teacher-list ' +
                'v-for="tec in teachersArr" ' +
                ':teacher="tec" ' +
                'v-model="teacher"' +
                ':key="tec.id" ></teacher-list>' +
              '<workSet-checkbox ' +
                'v-for="itm in typeOfWorkSetArr" ' +
                'v-model="typeOfWorkSet" ' +
                ':val="itm.value">{{ itm.label }}</workSet-checkbox>' +
              '<input type="text" placeholder="Задание" v-model="task"/><br/>' +
              // '<input type="text" placeholder="Расписание" v-model="schedule"/><br/>' +
              // '<input type="text" placeholder="Ресурсы" v-model="resources"/><br/>' +
              '<input type="button" value="Save" @click="addassign"/><br/>' +
            '</div>',
  methods:{
    addassign: function () {
      let assignment = {
        title: this.title,
        task: this.task,
        typeOfWorkSet: this.typeOfWorkSet,
        teacher: this.teacher
      };
      assignmentAddApi.save({}, assignment).then(result =>
          result.json().then(assignment => this.assignments.push(assignment)))
    }
  },
  created: function () {
    teacherApi.get().then(result => {
      result
          .json()
          .then(data => data
              .forEach(teacher => this.teachersArr.push(teacher)))
    })
  },
});

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
              '<div>{{ teacher.fullName }}</div>' +
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
              '<td><workSet-row v-for="workSet in assignment.typeOfWorkSet" :workSets="workSet"/></td>' +
              '<td>{{ assignment.task }}</td>' +
              '<td>{{ assignment.schedule }}</td>' +
              '<td>{{ assignment.resources }}</td>' +
            '</tr>'
});

Vue.component('assignments-table',{
  props:['assignments'],
  template: '<div>' +
              '<table border="1">'+
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
              '</table>' +
              '<assignment-form :assignments="assignments" />' +
            '</div>',
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

