1\.專案介紹

&nbsp;	嚴謹的權限控管

&nbsp;		區分管理員與保險員雙重角色

&nbsp;		基於Spring Security實作路徑級別的存取控制，並採用BCrypt加密保障帳號安全

&nbsp;	自動化業務邏輯

&nbsp;		自動生成唯一保單編號，格式：PN+日期+流水號

&nbsp;		系統啟動時自動掃描並加密舊有的明文密碼

&nbsp;	高效的前後端互動

&nbsp;		使用AJAX非同步技術，實現無刷新頁面的資料更新與表單提交

&nbsp;		前端整合Vue與jQuery，提供流暢的動態操作體驗

&nbsp;	資料完整性保障

&nbsp;		使用Spring Data JPA處理複雜關聯

&nbsp;		實作@Transactional交易管理，確保多表寫入時的原子性



2\.技術

&nbsp;	後端

&nbsp;		核心框架：Spring Boot

&nbsp;		語言：Java17

&nbsp;		安全性：Spring Security、BCrypt

&nbsp;		資料庫：MSSQL

&nbsp;		ORM框架：Spring Data JPA

&nbsp;		SQL工具：JDBC Template

&nbsp;		API設計：RESTful

&nbsp;		依賴管理：Maven

&nbsp;	前端

&nbsp;		核心邏輯：Vue、JQuery、AJAX

&nbsp;		互動元件：SweetAlert

&nbsp;		UI框架：Bootstrap

&nbsp;	維運與部署

&nbsp;		版本控制：Git

&nbsp;		容器化：Docker、Docker Compose



3\.啟動方式

&nbsp;	前置需求

&nbsp;		A.安裝Docker Desktop並啟動，https://www.docker.com/products/docker-desktop/

&nbsp;		B.到https://github.com/DennisHuang0607/InsuranceSystem.git，下載專案並解壓縮

&nbsp;	啟動步驟

&nbsp;		C.設定環境變數：在專案根目錄建立.env檔案，並設定資料庫密碼(大寫+小寫+數字+特殊符號+至少八個字元)->SA\_PASSWORD=*你的密碼*

&nbsp;		D.打包專案：./mvnw clean package -DskipTests

&nbsp;		E.準備JAR檔：進入target資料夾，將生成的jar檔重新命名為insurancesystem.jar

&nbsp;		F.啟動容器：docker-compose up -d

&nbsp;		G.訪問系統：首頁->http://localhost:8080/insurancesystem/home，資料庫連接埠->3306

&nbsp;	視情況使用

&nbsp;		H.停止並刪除容器，同時刪除資料卷：docker-compose down -v





