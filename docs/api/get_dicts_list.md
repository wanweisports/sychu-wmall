### 请求地址

/dict/getDicts

### 请求参数
 
* dictName：字典名称、字符串类型（此值参照sys_dict表的dictName字段）

### 返回结果

```json
{
    "dicts" : [{
        "dictName"  : "String 字典名称",
        "dictId"    : "Integer字典值",
        "dictValue" : "String 字典文案"
    }, {
        "dictName"  : "String 字典名称",
        "dictId"    : "Integer字典值",
        "dictValue" : "String 字典文案"
    }]
}
```