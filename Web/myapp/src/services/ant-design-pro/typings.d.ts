// @ts-ignore
/* eslint-disable */

declare namespace API {
  /**
   * 创建用户变量
   */
  type CreateParams = {
    username?: string;
    userAccount: string;
    userPassword?: string;
    avatarUrl?: string;
    gender:number;
    phone: string;
    email: string;
    userStatus: number;
    createTime: Date;
    userRole: number;
    planetCode: string;
  };

  type ModifyPasswordParam={
    password?: string;
    newPassword?: string;
  }

  type CurrentUser = {
    id?: number;
    planetCode?: string;
    username?: string;
    avatarUrl?: string;
    userAccount?: string;
    gender?: number;
    phone?: string;
    email?: string;
    userStatus?: number;
    createTime?: Date;
    userRole?: number;

  };

  type SearchUser = {
    current?: number;
    pageSize?: number;
    keyWord?: number;
    username?: string;
  };
  /**
   * 通用返回类
   */
  type BaseResponse<T>={
    code: number,
    data: T,
    message: string,
    description: string;
  }


  /**
   * 删除的参数
   */
  type DeleteParam = {
    id: number;
  };


  type SearchUserParam = {
    username?: string;
  };

  type LoginResult = {
    status?: string;
    type?: string;
    currentAuthority?: string;
  };

  type RegisterResult = number;

  type PageParams = {
    current?: number;
    pageSize?: number;
  };

  type RuleListItem = {
    key?: number;
    disabled?: boolean;
    href?: string;
    avatar?: string;
    name?: string;
    owner?: string;
    desc?: string;
    callNo?: number;
    status?: number;
    updatedAt?: string;
    createdAt?: string;
    progress?: number;
  };

  type RuleList = {
    data?: RuleListItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };

  type FakeCaptcha = {
    code?: number;
    status?: string;
  };

  type LoginParams = {
    userAccount?: string;
    password?: string;
    autoLogin?: boolean;
    type?: string;
  };
  type RegisterParams = {
    planetCode?: string;
    username?: string;
    userAccount?: string;
    password?: string;
    checkPassword?: string;
    type?: string;
  };

  type ErrorResponse = {
    /** 业务约定的错误码 */
    errorCode: string;
    /** 业务上的错误信息 */
    errorMessage?: string;
    /** 业务上的请求是否成功 */
    success?: boolean;
  };

  type NoticeIconList = {
    data?: NoticeIconItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };

  type NoticeIconItemType = 'notification' | 'message' | 'event';

  type NoticeIconItem = {
    id?: string;
    extra?: string;
    key?: string;
    read?: boolean;
    avatar?: string;
    title?: string;
    status?: string;
    datetime?: string;
    description?: string;
    type?: NoticeIconItemType;
  };
}
