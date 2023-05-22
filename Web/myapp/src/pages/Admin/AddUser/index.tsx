import { LockOutlined, MailOutlined, PhoneOutlined, UserOutlined } from '@ant-design/icons';
import { LoginForm, ProFormText } from '@ant-design/pro-components';
import { ProFormSelect } from '@ant-design/pro-form';
import { create } from '@/services/ant-design-pro/api';
import { SYSTEM_LOGO } from '@/constant';
import { selectAvatarUrl, selectGender, selectUserRole, selectUserStatus } from '@/constant';
import {message} from "antd";

export default () => {
  const handleCreate = async (values: API.CreateParams) => {
    try {
      // 创建用户
      const suc = await create(values);
      if (suc) {
        const defaultLoginSuccessMessage = '创建成功！';
        message.success(defaultLoginSuccessMessage);
        location.reload();
        return;
      }
    } catch (error: any) {
      const defaultLoginFailureMessage = '注册失败，请重试！可能编号已经被注册了，换一个编号试试吧';
      //alert("可能编号已经被注册了，换一个编号试试吧");
      message.error(defaultLoginFailureMessage);
    }
  };
  return (
    <div style={{ backgroundColor: 'white' }}>
      <LoginForm
        submitter={{
          searchConfig: {
            submitText: '新增用户',
          },
        }}
        logo={SYSTEM_LOGO}
        title="添加新病友"
        subTitle="填写病人信息"
        onFinish={async (values) => {
          await handleCreate(values as API.CreateParams);
        }}
      >

        {
          <>
            <ProFormText
              name="username"
              fieldProps={{
                size: 'large',
                prefix: <UserOutlined className={'prefixIcon'} />,
              }}
              placeholder="用户名"
              rules={[
                {
                  required: true,
                  message: '用户名不能为空!',
                },
              ]}
            />
            <ProFormText
              name="userAccount"
              fieldProps={{
                size: 'large',
                prefix: <UserOutlined className={'prefixIcon'} />,
              }}
              placeholder="用户账户 "
              rules={[
                {
                  required: true,
                  message: '用户账户不能为空!',
                },
                {
                  min: 4,
                  message: '用户账户长度不小于4位',
                },
              ]}
            />
            <ProFormText.Password
              name="userPassword"
              fieldProps={{
                size: 'large',
                prefix: <LockOutlined className={'prefixIcon'} />,
              }}
              placeholder={'密码'}
              rules={[
                {
                  required: true,
                  message: '请输入密码！',
                },
                {
                  min: 8,
                  message: '密码长度不得小于8',
                },
              ]}
            />
            <ProFormText
              name="planetCode"
              fieldProps={{
                size: 'large',
                prefix: <UserOutlined className={'prefixIcon'} />,
              }}
              placeholder={'想要哪个床位号 '}
              rules={[
                {
                  max: 15,
                  message: '用户编号不得大于15位',
                },
                {
                  required: true,
                  message: '用户编号必填',
                },
              ]}
            />

            <ProFormText
              name="phone"
              fieldProps={{
                size: 'large',
                prefix: <PhoneOutlined className={'prefixIcon'} />,
              }}
              placeholder={'用户手机号 '}
              rules={[
                {
                  required: true,
                  message: '手机号码不能为空!',
                },
              ]}
            />
            <ProFormText
              name="email"
              fieldProps={{
                size: 'large',
                prefix: <MailOutlined className={'prefixIcon'} />,
              }}
              placeholder={'用户邮箱 '}
              rules={[
                {
                  required: true,
                  message: '邮箱不能为空!',
                },
              ]}
            />
            <ProFormSelect
              name="userStatus"
              fieldProps={{
                size: 'large',
              }}
              label="用户状态"
              options={selectUserStatus}
              placeholder={'选择用户状态'}
              rules={[
                {
                  required: true,
                  message: '请选择用户状态',
                },
              ]}
            />
            <ProFormSelect
              name="avatarUrl"
              fieldProps={{
                size: 'large',
              }}
              label="用户头像"
              options={selectAvatarUrl}
              placeholder={'请选择用户头像 '}
              rules={[
                {
                  required: true,
                  message: '请输入选择用户头像!',
                },
              ]}
            />
            <ProFormSelect
              name="gender"
              fieldProps={{
                size: 'large',
              }}
              label="性别"
              options={selectGender}
              placeholder="请选择性别"
              rules={[
                {
                  required: true,
                  message: '请选择性别',
                },
              ]}
            />
            <ProFormSelect
              name="userRole"
              fieldProps={{
                size: 'large',
              }}
              label="用户角色"
              options={selectUserRole}
              placeholder={'选择用户角色'}
              rules={[
                {
                  required: true,
                  message: '请选择用户角色',
                },
              ]}
            />
          </>
        }
        <div
          style={{
            marginBlockEnd: 24,
          }}
        />
      </LoginForm>
    </div>
  );
};
