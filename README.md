## Vergleich

<table>
<tr>
  <td> </td><td> React </td> <td> Vue </td>
</tr>
<tr>
  <td> Community </td>
  <td> - 330.000 Stackoverflow Fragen <br> - 174.000 Github Stars <br> - 16.000.000 wöchentl. Downloads <br> - 41% Nutzung unter prof. Programmierern </td>
  <td> - 80.000 Stackoverflow Fragen <br> - 187.000 Github Stars <br> - 3.300.000 wöchentl. Downloads <br> - 20% Nutzung unter prof. Programmierern </td>
</tr>
<tr>
  <td> Erstellung </td>
  <td> 
    <pre lang="bash">$ npx create-react-app appname --template typescript</pre>
  </td>
 <td> 
    <pre lang="bash">$ npm install -g @vue/cli 
$ vue create appname</pre>
  </td>
</tr>
</table>

### Struktur
#### React
Filename: MyComponent.tsx
```jsx
import { ... } from  "../../Something";
import "./style.scss";
...

const  MyComponent = () => {

  // ts-code

  return (
    <div>Test</div> // tsx
  );
};

export  default  React.memo(MyComponent);
```

#### Vue
Filename: MyComponent.vue
```jsx
<template>
  <div>Test</div> // html
</template>

<script lang="ts">
import { ... } from "../../Something";

export default {
  name: "MyComponent",
  data: () => ({
    key: value,
  }),
};
</script>

<style lang="scss" scoped>
  // scss
</style>
```

### Router
#### React
```jsx
import { BrowserRouter, Routes, Route, Navigate } from  "react-router-dom";
import  MyView  from  "./components/MyView";
...

const  MyComponent = () => {
  ...
  
  return (
    ...
    
    <BrowserRouter>
      <Routes>
        <Route path="/myPath/subPath"  element={<MyView/>} />
        <Route path="/path"  element={<OtherView/>} />
        ...
      </Routes>
    </BrowserRouter>
    
    ...
  );
};
```
#### Vue
File: `router/index.ts`
```jsx
import  Vue  from  'vue';
import  VueRouter, { RouteConfig } from  'vue-router';

Vue.use(VueRouter);

const  routes: Array<RouteConfig> = [
  {
    path: "/myPath/subPath",
    component: MyView,
  },
  {
    path: "/path",
    component: OtherView,
  },
]

const router = new VueRouter({
  routes,
});
```
File: `main.ts`
```jsx
import  Vue  from  'vue';
import  App  from  './App.vue';
import  router  from  './router';

Vue.config.productionTip = false;

new  Vue({
  router,
  render: (h) => h(App),
}).$mount('#app');
```
File: `App.vue`
```jsx
<template>
  ...
  <router-view />
  ...
</template>
```
### State-Management
#### React
##### Kleinere State-Handling
```jsx
const MyComponent = () => {
  const [value, setValue] = React.useState<boolean>(false); // false ist initialer State

  // loading ist der State der jeglichen Wert annehmen kann
  // setLoading ist die Funktion zum Updaten des States

  const myFunction = () => {
    setValue(true);
    // State-update => rerendert das HTML
  }

  return (
    <div onClick={myFunction}>{value}</div>
  );
}
```
##### Komplexere State-Handling
```jsx
const initialState = {
  value: "",
  loading: false,
}

const MyComponent = () => {
  const [state, dispatch] = React.useReducer(myReducer, initialState);

  // loading ist der State der jeglichen Wert annehmen kann
  // setLoading ist die Funktion zum Updaten des States

  const handleChange = () => {
    dispatch({type: Actions.SET_LOADING});
    setTimeout(() => dispatch({type: Actions.ADD_TO_VALUE, value: "My own value"}), 3000);
  }

  return (
    <div onClick={handleChange}>
      <div>{state.value}</div>
      <div>{state.loading}</div>
    </div>
  );
}
```
File: `MyReducer.ts`
```jsx
export const Actions = {
  SET_LOADING: "SET_LOADING",
  ADD_TO_VALUE: "ADD_TO_VALUE",
};

export  const  myReducer = (state: any, action: Action) => {
  switch (action.type) {
    case  Actions.SET_LOADING: {
      return {
        ...state,
        loading: true, 
      };
    }
    case  Actions.ADD_TO_VALUE: {
      return {
        loading: false,
        value: action.value,
      };
    }
  }
  default:
    return state;
}
```
#### Vue
##### Store
File: `store/index.ts`
```jsx
import  Vue  from  "vue";
import  Vuex  from  "vuex";

Vue.use(Vuex);  

export  default  new  Vuex.Store({
  state: {
    loading: false,
    value: "",
  },
  getters: {
    loading: (state) => {
      return  state.loading;
  },
    value: (state) => {
      return  state.value;
    },
  },
  mutations: {
    SET_LOADING(state) {
      state.loading = true;
    },
    ADD_TO_VALUE(state, value) {
      state.loading = false;
      state.value = value;
    },
  },
  actions: {
    LOAD({ commit }) {
      commit("SET_LOADING");
    },
    SET_VALUE({ commit }, value) {
      commit("ADD_TO_VALUE", value);
    },
  },
});
```
##### Verwendung
```jsx
<template>
  <div @click="handleChange()">
    <div>{{value}}</div>
    <div>{{loading}}</div>
  </div>
</template>

<script lang="ts">
import { mapGetters } from  "vuex";

export default {
  name: "MyComponent",
  methods: {
    handleChange() => {
      this.$store.dispatch("LOAD");
      setTimeout(() => this.$store.dispatch("SET_VALUE", "My own value"), 3000);
    }
  },
  computed: {
    ...mapGetters([
      "loading",
      "value",
    ])
  },
};
</script>

<style lang="scss" scoped>
  // scss
</style>
```
