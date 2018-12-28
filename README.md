# 从零开始造Spring

[![travis](https://travis-ci.org/jachinlin/springlite.svg?branch=master)](https://travis-ci.org/jachinlin/springlite)
[![codecov](https://codecov.io/gh/jachinlin/springlite/branch/master/graph/badge.svg)](https://codecov.io/gh/jachinlin/springlite)

采用测试驱动开发TDD方式，实现一个具有IOC和AOP核心功能的Spring —— springlite：
1. 为了实现新功能，编写测试案例
2. 测试失败
3. 编写代码满足功能需求，通过测试
4. 如果需要的，重构
5. 重复上面操作

springlite实现的功能有：
1. 基于XML的BeanFactory
2. 实现setter注入
3. 实现构造函数注入
4. 实现注解和自动扫描
5. 用cglib实现AOP
6. 用Java自动代理实现AOP

开始吧～



## Basic BeanFactory



