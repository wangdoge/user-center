export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        name: 'login',
        path: '/user/login',
        component: './user/Login',
      },

      {
        name: 'register',
        path: '/user/register',
        component: './user/Register',
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },
  {
    path: '/Admin',
    name: 'admin',
    icon: 'crown',
    access: 'canAdmin',
    routes: [

      {
        path: '/admin/user-manage',
        name: '用户管理',
        icon: 'smile',
        component: './Admin/UserManage',
      },
      {
        path: '/admin/add-user',
        name: '新增用户',
        component: './Admin/AddUser',
      },
      {
        component: './404',
      },
    ],
  },

  {
    path: '/user-info',
    name: '用户界面',
    icon: 'smile',
    component: './UserInfo',
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    component: './404',
  },
];
