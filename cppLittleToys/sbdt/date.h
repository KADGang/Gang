#pragma once
#include <iostream>

using namespace std;

class date {
private:
	int year;
	int month;
	int day;
	static int DATE[];
public:
	date(int YEAR, int MONTH, int DAY);
	date();
	friend ostream& operator<<(ostream& output, const date& date);
	friend istream& operator>>(istream& input, date& date);
	bool leap_year(int year);//判断闰年
	bool valid();//判断日期是否有效
	date& operator = (const date& date);
	date operator + (int Day);//Day天后的日期
	int operator - (date& date);//两日期相差天数
	date operator - (int Day);//Day天前的日期
	bool operator > (date& date);
	bool operator < (date& date);
	bool operator == (date& date);//判断日期是否相同
};

