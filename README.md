# 🧼 Customer Deduplication (Scala + Spark)

A production-style template for **customer identity resolution and deduplication** using **Apache Spark (Scala)**.

It standardizes inputs, generates blocking keys, performs **deterministic + fuzzy matching** (Jaro–Winkler + Soundex), and selects a **golden record** per cluster via **survivorship rules**.

## ✨ Features
- Email/phone **normalization**
- Phonetic key (**Soundex**) on last name
- **Blocking** on email / phone / soundex
- **Fuzzy** name + address (Jaro–Winkler via Apache Commons Text)
- **Survivorship**: preferred source → most recent → most complete
- Outputs both **golden customers** and **duplicate pairs**

## 🗂 Project Structure
```
customer-deduplication-scala/
├─ build.sbt
├─ project/plugins.sbt          # sbt-assembly
├─ src/
│  ├─ main/scala/com/parthmundhwa/dedup/
│  │  ├─ Utils.scala
│  │  └─ DedupJob.scala
│  └─ main/resources/rules/survivorship.yaml
└─ data/
   ├─ raw/customers.csv         # sample data (with dupes)
   └─ curated/.keep             # outputs written here
```

## 🚀 Run Locally (sbt)
```bash
# 1) run with local[*]
sbt run

# 2) build an uber-jar (fat jar)
sbt assembly

# 3) submit with spark
spark-submit --class com.parthmundhwa.dedup.DedupJob   --master local[*]   target/scala-2.12/customer-deduplication-scala-assembly-0.1.0-SNAPSHOT.jar
```

Outputs:
- `data/curated/customers_deduped.csv`
- `data/curated/duplicate_pairs.csv`

## ⚙️ Configure
Tune thresholds, blocking keys, and survivorship priorities in:
```
src/main/resources/rules/survivorship.yaml
```

## 📌 Notes
- For true connected components clustering, add GraphFrames (not included here) or run a transitive closure job.
- Replace sample data with your feeds; wire into Airflow or Spark on Kubernetes for production.

## 🪪 License
MIT © 2025 Parth Mundhwa
