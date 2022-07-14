function renderEMployee(
	data,
	totalEPFContributionRemitted,
	totalEPSContributionRemitted,
	totalEPFEPSContributionRemitted,
	totalNumber,
	footerFunc
) {
	const html = `<div class="flex flex-col">
      <div class="flex
       flex-col text-[28px]">
        <div class="flex h-[56px]">
          <div class="w-[524px] border border-black flex pl-[12px] items-center">
            Name of Establishment
          </div>
          <div class="w-[1706px] border border-black -ml-px flex pl-[12px] items-center">
            PRIMEWINGS SERVICE PRIVATE LIMITED
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">
            Establishment Id
          </div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">
            MRNOI2513599000
          </div>
          <div class="w-[484px] border pl-[12px] border-black -ml-px flex items-center">LIN</div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">
            1867777921
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">Wage Month</div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">
            ${data.wageMonth || ''}
          </div>
          <div class="w-[484px] border pl-[12px] border-black -ml-px flex items-center">
            Return Month
          </div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">
            ${data.returnMonth || ''}
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">
            Contribution Rate (%)
          </div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">12</div>
          <div class="w-[484px] border pl-[12px] border-black -ml-px flex items-center">
            ECR Type
          </div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">ECR</div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">
            Salary Disbursement Date
          </div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">
            ${data.salaryDisbursementDate || ''}
          </div>
          <div class="w-[484px] border pl-[12px] border-black -ml-px flex items-center">
            Uploaded Date Time
          </div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">
            ${data.uploadedDateTime || ''}
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">
            Exemption Status
          </div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">
            ${data.exemptionStatus || ''}
          </div>
          <div class="w-[484px] border pl-[12px] border-black -ml-px flex items-center">
            TRRN Number
          </div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">
            ${data.trrnNumber || ''}
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">Remarks</div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">
            ${data.remarks || ''}
          </div>
          <div class="w-[484px] border pl-[12px] border-black -ml-px flex items-center">
            ECR Id
          </div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">
            ${data.ecrId || ''}
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">
            Total Members
          </div>
          <div class="w-[612px] border pl-[12px] border-black -ml-px flex items-center">${
						totalNumber || ''
					}</div>
          <div class="w-[484px] border pl-[12px] border-black -ml-px flex items-center">
           ${data.aadhaarNotSeededMember ? 'Aadhaar Not Seeded Member' : ''}
          </div>
          <div class="w-[611px] border pl-[12px] border-black ml-[2px] flex items-center">
          ${data.aadhaarNotSeededMember || ''}
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[2229px] border pl-[12px] font-bold border-black flex items-center">
            Contribution and Remittance Details (In Rupees) :
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">
            Total EPF Contribution Remitted
          </div>
          <div
            class="w-[612px] border pr-[12px] border-black -ml-px flex justify-end items-center"
          >
          ${totalEPFContributionRemitted || ''}
          </div>
          <div class="w-[484px] border pl-[12px] border-black -ml-px flex items-center">
            Total EPS Contribution Remitted
          </div>
          <div
            class="w-[612px] border pr-[12px] border-black -ml-px flex justify-end items-center"
          >
            ${totalEPSContributionRemitted || ''}
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">
            Total EPF-EPS Contribution Remitted
          </div>
          <div
            class="w-[612px] border pr-[12px] border-black -ml-px flex justify-end items-center"
          >
          ${totalEPFEPSContributionRemitted || ''}
          </div>
          <div class="w-[484px] border pl-[12px] border-black -ml-px flex items-center">
            Total Refund Advance
          </div>
          <div
            class="w-[612px] border pr-[12px] border-black -ml-px flex justify-end items-center"
          >
            ${data.totalRefundAdvance}
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[2229px] border pl-[12px] font-bold border-black flex items-center">
            PMRPY Upfront Benefit Details (In Rupees) :
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">
            Total PMRPY Upfront EPF Amount
          </div>
          <div
            class="w-[612px] border pr-[12px] border-black -ml-px flex justify-end items-center"
          >
            0
          </div>
          <div class="w-[484px] border p-[12px] border-black -ml-px flex items-center">
            Total PMRPY Upfront EPS Amount
          </div>
          <div
            class="w-[612px] border pr-[12px] border-black -ml-px flex justify-end items-center"
          >
            0
          </div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">
            PMRPY benefit remarks
          </div>
          <div class="w-[1706px] border pl-[12px] border-black -ml-px flex items-center">NA</div>
        </div>
        <div class="flex h-[56px] -mt-px">
          <div class="w-[2229px] border pl-[12px] font-bold border-black flex items-center">
            ABRY Upfront Benefit Details (In Rupees) :
          </div>
        </div>
        <div class="flex h-[112px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">
            Total ABRY benefit Amount
          </div>
          <div class="flex w-[1706px] flex-col">
            <div class="flex h-[56px]">
              <div class="w-[612px] border border-black -ml-px flex justify-center items-center">
                Employee EPF Share
              </div>
              <div class="w-[484px] border border-black -ml-px justify-center flex items-center">
                Employer EPS Share
              </div>
              <div class="w-[612px] border border-black -ml-px flex justify-center items-center">
                Employer EPF Share
              </div>
            </div>
            <div class="flex h-[56px]">
              <div
                class="w-[612px] border pr-[12px] border-black border-t-0 -ml-px flex justify-end items-center"
              >
                ${data.employeeEPFShare || ''}
              </div>
              <div
                class="w-[484px] border p-[12px] border-black border-t-0 -ml-px justify-end flex items-center"
              >
                ${data.employerEPSShare || ''}
              </div>
              <div
                class="w-[612px] border pr-[12px] border-black border-t-0 -ml-px flex justify-end items-center"
              >
                ${data.employerEPFShare}
              </div>
            </div>
          </div>
        </div>
        <div class="flex h-[63px] -mt-px">
          <div class="w-[524px] border pl-[12px] border-black flex items-center">
            ABRY benefit remarks
          </div>
          <div
            class="w-[1706px] border pl-[12px] leading-[28px] border-black -ml-px flex items-center"
          >
            ${data.abryBenefitRemarks || ''}
          </div>
        </div>
      </div>
    </div>
    ${footerFunc(1)}
    `

	document.querySelector('.employeeData').innerHTML = html
}

