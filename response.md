### 完成內容
- - -
1. 完成目標

   * [x] List all pharmacies open at a specific time and on a day of the week if requested. (藥房營業時間)
   * [x] List all masks sold by a given pharmacy, sorted by mask name or price.(取得商店BY口罩價格)
   * [x] List all pharmacies with more or less than x mask products within a price range.(取得店家口罩種類)
   * [x] The top x users by total transaction amount of masks within a date range.(取口罩最大交易量排名)
   * [x] The total number of masks and dollar value of transactions within a date range.(取口罩最大交易量排名)
   * [x] Search for pharmacies or masks by name, ranked by relevance to the search term.(取時間範圍內口罩賣出數量及價格)
   * [x] Process a user purchases a mask from a pharmacy, and handle all relevant data changes in an atomic transaction.(尋找商店或是口罩關鍵字)
2. 已部屬至 movemessage.com
3. postman:https://www.getpostman.com/collections/007c9931fdb1549bbff3

# 部屬教學
- - -
1.git clone https://github.com/scanAskSolve/phantomMask.git
2. use gradle to build 
3. set application.properties to  mysql connect
4. create mysql Datbase phantom_mask
3. get phantomMask-0.0.1-SNAPSHOT.jar from phantomMask/build/libs/phantomMask-0.0.1-SNAPSHOT.jar
4. install java 8 to run jar
5. use postman to test
